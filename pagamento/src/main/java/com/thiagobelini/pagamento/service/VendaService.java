package com.thiagobelini.pagamento.service;

import com.thiagobelini.pagamento.entity.ProdutoVenda;
import com.thiagobelini.pagamento.entity.Venda;
import com.thiagobelini.pagamento.entity.data.vo.VendaVO;
import com.thiagobelini.pagamento.exception.ResourceNotFoundException;
import com.thiagobelini.pagamento.repository.ProdutoVendaRepository;
import com.thiagobelini.pagamento.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository){
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaVO create(VendaVO vendaVO) {
        Venda venda = vendaRepository.save(Venda.create(vendaVO));
        List<ProdutoVenda> produtosSalvos = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);
        return VendaVO.create(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable) {
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaVO);
    }

    private VendaVO convertToVendaVO(Venda venda) {
        return VendaVO.create(venda);
    }

    public VendaVO findById(Long id){
        var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return VendaVO.create(entity);
    }

}
