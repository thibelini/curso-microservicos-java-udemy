package com.thiagobelini.pagamento.entity.data.vo;

import com.thiagobelini.pagamento.entity.Venda;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

    private static final long serialVersionUID = -2921534414154200766L;
    private Long id;
    private Date data;
    private List<ProdutoVendaVO> produtos;
    private Double valorTotal;

    public static VendaVO create(Venda venda) {
        return new ModelMapper().map(venda, VendaVO.class);
    }

}
