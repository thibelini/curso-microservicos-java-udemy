package com.thiagobelini.pagamento.entity;


import com.thiagobelini.pagamento.entity.data.vo.VendaVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Venda implements Serializable {
    private static final long serialVersionUID = -6740590476092696051L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "data", nullable = false)
    private Date data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = {CascadeType.REFRESH})
    private List<ProdutoVenda> produtos;

    @Column(name = "valortotal", nullable = false, length = 10)
    private Double valorTotal;

    public static Venda create(VendaVO vendaVO) {
        return new ModelMapper().map(vendaVO, Venda.class);
    }
}
