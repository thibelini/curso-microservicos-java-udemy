package com.thiagobelini.pagamento.entity.data.vo;

import com.thiagobelini.pagamento.entity.ProdutoVenda;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {

    private static final long serialVersionUID = 7801009605550414001L;
    private Long id;
    private Long idProduto;
    private Integer quantidade;

    public static ProdutoVendaVO create(ProdutoVenda produtoVenda){
        return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
    }
}
