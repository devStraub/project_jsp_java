package com.cgpm.api.base;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cgpm.api.utils.ViewUtils;

/**
 * @author michel.pech
 */

@Controller
public abstract class BaseViewController<T extends PersistenceInterface<ID>, ID> {		
	
	private String moduleName;
	private String listPage;
	private String editPage;
			
	private List<T> entityList;
	private T entity;
	private ID id;
	private boolean edit;
	
	private Model model;
	private ViewUtils utils;
			
    public BaseViewController(String moduleName, String listPage, String editPage) {
		this.moduleName = moduleName;
    	this.listPage = listPage;
		this.editPage = editPage;
		this.utils = new ViewUtils();
	}
    
    public abstract void save();
    
    @PostMapping("/remove")
    public abstract String remove(@ModelAttribute("entity") T entity);

    @GetMapping("/list")
    public String getListView(Model model) {
    	this.viewInit(model);
    	
    	this.getListViewOperations();
        
        return listPage;
    }       

	@GetMapping("/edit/{id}")
	public String getEditView(@PathVariable Long id, Model model) {        
		this.viewInit(model);
		
		this.getEditViewOperations(id);
        
        return editPage;
	}

	@PostMapping("/edit")
	public String postEditView(@ModelAttribute("entity") T entity, Model model) {
		this.viewInit(model);
		
		this.setEntity(entity);
        
        this.postEditViewOperations();   
        
        return editPage;
	}      
    
    public void viewInit(Model model) {
    	this.setModel(model);
    	
    	this.getModel().addAttribute("utils", getUtils());
    	this.getModel().addAttribute("view", this);
    }
    
    public void getListViewOperations() {}  
    
    public void getEditViewOperations(Long id) {}
    
    public String postEditViewOperations() {
    	return this.getEditPage();
    }
    
    public String goList() {
    	return "redirect:/" + moduleName + "/list";
    }    
    
    public String goEdit(String id) {
    	return "redirect:/" + moduleName + "/edit/" + id;
    } 
        
	public String getModuleName() {
		return moduleName;
	}

	public String getListPage() {
		return listPage;
	}

	public String getEditPage() {
		return editPage;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}  
    
	public ViewUtils getUtils() {
		return utils;
	}

	public void setUtils(ViewUtils utils) {
		this.utils = utils;
	}

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}		
		
}
