package com.cgpm.api.controllers.view;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgpm.api.base.BaseViewController;
import com.cgpm.api.model.entities.Pessoa;
import com.cgpm.api.model.entities.Projeto;
import com.cgpm.api.services.PessoaService;
import com.cgpm.api.services.ProjetoService;
import com.cgpm.api.type.RiscoType;
import com.cgpm.api.type.StatusType;

@Controller
@RequestMapping("/projeto")
public class ProjetoViewController extends BaseViewController<Projeto, Long> {	

	@Autowired
	private ProjetoService projetoService;	
	@Autowired
	private PessoaService pessoaService;
	
	private List<Pessoa> funcionarioList;
	private List<StatusType> statusList;
	private List<RiscoType> riscoList;
	
    public ProjetoViewController(@Value("projeto") String moduleName, @Value("projetoList") String listPage, @Value("projetoEdit") String editPage) {
        super(moduleName,listPage, editPage);
    }
    
    @Override
    public void save() {
    	if (isEdit()) {
    		getEntity().setId(getId());
    		setEntity(projetoService.update(getEntity()));    		
    	}
    	else {
    		setEntity(projetoService.insert(getEntity()));
    	}
    }
    
    @Override
    public String remove(Projeto entity) {
    	projetoService.deleteById(entity.getId());
    	return "redirect:/projeto/list";
    }
    
	@Override
	public void getListViewOperations() {
    	setEntityList(projetoService.findAll());    	
        getModel().addAttribute("entityList", getEntityList());		
	}   
	
	@Override
	public void getEditViewOperations(Long id) {
		setEntity(projetoService.findById(id).orElse(new Projeto()));		
		getModel().addAttribute("entity", getEntity());
		
		setId(getEntity().getId());
		getModel().addAttribute("objectId", getId());		
		
		setEdit(getEntity().getId() != null ? true : false);
		getModel().addAttribute("isEdit", isEdit());
		
		this.setFuncionarioList(pessoaService.findFuncionarios());
		getModel().addAttribute("funcionarioList", this.getFuncionarioList());	
		
		this.setStatusList(Arrays.asList(StatusType.values()));
		getModel().addAttribute("statusList", this.getStatusList());	
		
		this.setRiscoList(Arrays.asList(RiscoType.values()));
		getModel().addAttribute("riscoList", this.getRiscoList());		
	}
	
	@Override
	public String postEditViewOperations() {				
		this.save();				
		return getEditPage() + "/" + getEntity().getId();
	}
	
	public String getStatusLabel(String status) {
		List<StatusType> typeList = Arrays.asList(StatusType.values());
		StatusType type = typeList.stream().filter(s -> s.name().equals(status)).findFirst().orElse(null);
		
		if (!ObjectUtils.isEmpty(type)) {
			return type.getLabel();
		}
		
		return null;
	}
	
	public String getRiscoLabel(String risco) {
		List<RiscoType> typeList = Arrays.asList(RiscoType.values());
		
		RiscoType type = typeList.stream().filter(s -> s.name().equals(risco)).findFirst().orElse(null);
		
		if (!ObjectUtils.isEmpty(type)) {
			return type.getLabel();
		}
		
		return null;		
	}	

	public List<Pessoa> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Pessoa> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	public List<StatusType> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<StatusType> statusList) {
		this.statusList = statusList;
	}

	public List<RiscoType> getRiscoList() {
		return riscoList;
	}

	public void setRiscoList(List<RiscoType> riscoList) {
		this.riscoList = riscoList;
	}   		
	
}
