package proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import proj.controller.staticMethod.CommonMethod;
import proj.entity.Category;
import proj.entity.StringProperties;
import proj.form.Filter.CategoryFilterForm;
import proj.service.CategoryService;
import proj.service.StringPropertiesService;
import proj.service.implementation.editor.CategoryEditor;
import proj.service.implementation.editor.StrPropEditor;
import proj.service.implementation.validator.CategoryValidator;

import javax.validation.Valid;

/**
 * Created by SCIP on 16.08.2016.
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StringPropertiesService stringPropertiesService;

    @ModelAttribute("category")
    public Category getCategory(){
        return new Category();
    }

    @ModelAttribute("filter")
    public CategoryFilterForm categoryFilterForm(){
        return new CategoryFilterForm();
    }

    @InitBinder("category")
    protected void inBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(new CategoryValidator(categoryService));
        webDataBinder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        webDataBinder.registerCustomEditor(StringProperties.class, new StrPropEditor(stringPropertiesService));
    }

    @RequestMapping("/admin/adminCategory")
    public String showCategory(Model model,
                               @PageableDefault(5) Pageable pageable,
                               @ModelAttribute("filter") CategoryFilterForm categoryFilterForm){
        model.addAttribute("categories", categoryService.findAll(pageable, categoryFilterForm));
        model.addAttribute("rootCategories", categoryService.findByRootCategoryTrue());
        return "adminCategory";
    }

    @RequestMapping("/admin/adminCategory/categoryWithProperty/{id}")
    public String showCategoryWithStringProperty(Model model,
                                                 @PathVariable int id,
                                                 @PageableDefault(5) Pageable pageable,
                                                 @ModelAttribute("filter") CategoryFilterForm categoryFilterForm){
        model.addAttribute("category", categoryService.findByIdWithAllFetch(id));
        model.addAttribute("stringProperties", stringPropertiesService.findCategoryWithStringProperty(pageable, categoryFilterForm, id));
        return "categoryWithProperty";
    }


    @Transactional
    @RequestMapping(value = "/admin/adminCategory/categoryWithProperty/propertyId/{catId}/{id}")
    public String savePropertyToCategory(@PathVariable("catId") int catId ,
                                         @PathVariable("id") int id,
                                         @PageableDefault(5) Pageable pageable,
                                         @ModelAttribute("filter") CategoryFilterForm categoryFilterForm){
        categoryService.savePropertyToCategory(pageable, categoryFilterForm, catId, id);
        return "redirect:/admin/adminCategory/categoryWithProperty/" + catId + CommonMethod.getParams(pageable, categoryFilterForm);
    }

    @Transactional
    @RequestMapping(value = "/admin/adminCategory/categoryWithProperty/removePropertyId/{catId}/{id}")
    public String removePropertyFromCategory(@PathVariable("catId") int catId ,
                                         @PathVariable("id") int id,
                                         @PageableDefault(5) Pageable pageable,
                                         @ModelAttribute("filter") CategoryFilterForm categoryFilterForm){
        categoryService.removePropertyFromCategory(pageable, categoryFilterForm, catId, id);
        return "redirect:/admin/adminCategory/categoryWithProperty/" + catId + CommonMethod.getParams(pageable, categoryFilterForm);
    }

    @RequestMapping(value = "/admin/adminCategory", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") @Valid Category category,
                       BindingResult bindingResult,
                       @PageableDefault(5) Pageable pageable,
                       @ModelAttribute("filter") CategoryFilterForm categoryFilterForm,
                       Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.findAll(pageable, categoryFilterForm));
            return "adminCategory";
        }
        categoryService.save(category);
        return "redirect:/admin/adminCategory" + CommonMethod.getParams(pageable, categoryFilterForm);
    }

    @Modifying
    @Transactional
    @RequestMapping("/admin/adminCategory/delete/{id}")
    public String deleteCategory(@PathVariable int id,
                              @PageableDefault(5) Pageable pageable,
                              @ModelAttribute("filter") CategoryFilterForm categoryFilterForm){
        categoryService.deleteById(id);
        return "redirect:/admin/adminCategory" + CommonMethod.getParams(pageable, categoryFilterForm);
    }

    @RequestMapping("/admin/adminCategory/update/{id}")
    public String updateCategory(@PathVariable int id, Model model,
                                 @PageableDefault(5) Pageable pageable,
                                 @ModelAttribute("filter") CategoryFilterForm categoryFilterForm){
        model.addAttribute("category", categoryService.findByIdFetchParentId(id));
        model.addAttribute("categories", categoryService.findAll(pageable, categoryFilterForm));
        model.addAttribute("rootCategories", categoryService.findByRootCategoryTrue());
        return "adminCategory";
    }
}
