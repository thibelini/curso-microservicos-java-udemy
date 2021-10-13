package com.thiagobelini.pagamento.entity;

import com.thiagobelini.pagamento.entity.data.vo.ProdutoVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto create(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, Produto.class);
    }

}
