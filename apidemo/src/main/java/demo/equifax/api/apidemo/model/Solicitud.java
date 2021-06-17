package demo.equifax.api.apidemo.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @Column(name = "id_solicitud", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @Column(name = "problema", nullable = false)
    private String problema;

    @ManyToOne
    @JoinColumn(name = "id_solicitante",nullable = true)
    private Usuario usuarioSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_responsable", nullable = true)
    private Usuario usuarioResponsable;

    @ManyToOne
    @JoinColumn(name = "id_centro", nullable = true)
    private Centro idCentro;
}
