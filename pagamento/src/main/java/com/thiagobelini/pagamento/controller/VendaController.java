package com.thiagobelini.pagamento.controller;

import com.thiagobelini.pagamento.entity.data.vo.VendaVO;
import com.thiagobelini.pagamento.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaService vendaService;
    private final PagedResourcesAssembler<VendaVO> assembler;

    @Autowired
    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaVO> assembler) {
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/yaml"})
    public VendaVO findById(@PathVariable("id") Long id){
        VendaVO VendaVO = vendaService.findById(id);
        VendaVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return VendaVO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/yaml"})
    public ResponseEntity<?> findByAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "12") int limit,
                                       @RequestParam(value = "direction", defaultValue = "asc") String direction){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "data"));
        Page<VendaVO> vendas = vendaService.findAll(pageable);
        vendas.stream().forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));
        PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(vendas);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(
            produces = {"application/json", "application/xml", "application/yaml"},
            consumes = {"application/json", "application/xml", "application/yaml"})
    public VendaVO create(@RequestBody VendaVO VendaVO) {
        VendaVO vendVO = vendaService.create(VendaVO);
        vendVO.add(linkTo(methodOn(VendaController.class).findById(vendVO.getId())).withSelfRel());
        return vendVO;
    }
}
