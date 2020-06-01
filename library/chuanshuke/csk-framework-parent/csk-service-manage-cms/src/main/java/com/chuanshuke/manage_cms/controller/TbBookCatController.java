package com.chuanshuke.manage_cms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuanshuke.api.cms.cat_manage.CatManageControllerApi;
import com.chuanshuke.framework.domain.cms.TbBookCat;
import com.chuanshuke.framework.domain.cms.ext.TbBookCatNode;
import com.chuanshuke.framework.domain.cms.response.CmsBookCatResult;
import com.chuanshuke.framework.model.response.QueryResponseResult;
import com.chuanshuke.framework.model.response.ResponseResult;
import com.chuanshuke.manage_cms.service.TbBookCatService;

@RestController
@RequestMapping("/cms/cat")
public class TbBookCatController implements CatManageControllerApi {
	
	@Autowired
	private TbBookCatService tbBookCatService;

	@Override
	@GetMapping("/list/{page}/{size}")
	public QueryResponseResult<TbBookCat> findList(@PathVariable("page") int page, 
			@PathVariable("size")int size) {
//		Pageable pageable = new PageRequest(page, size);
		return tbBookCatService.findList(page, size);
	}

	@PostMapping("/add")
	@Override
	public CmsBookCatResult add(@RequestBody TbBookCat tbBookCat) {
		return tbBookCatService.add(tbBookCat);
	}

	@GetMapping("/get/{id}")
	@Override
	public TbBookCat findById(@PathVariable Long id) {
		TbBookCat bookCat = tbBookCatService.getByid(id);
		return bookCat;
	}

	@PutMapping("/edit/{id}")
	@Override
	public CmsBookCatResult edit(Long id, TbBookCat tbBookCat) {
		
		return tbBookCatService.update(id, tbBookCat);
	}
	
	@Transactional
	@DeleteMapping("/delete/{id}")
	@Override
	public ResponseResult delete(@PathVariable("id")Long id) {
		
		return tbBookCatService.delete(id);
	}
	
	//分类叶子节点查询，用于选择分类
	@GetMapping("/select")
	public TbBookCatNode findTbBookCatList() {
		return tbBookCatService.findTbBookCatList();
	}

}
