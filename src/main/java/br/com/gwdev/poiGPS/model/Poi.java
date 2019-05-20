package br.com.gwdev.poiGPS.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "POI",schema = "public")
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descpoi")
    private String descPoi;

    @Column
    private Integer coordenadaX;

    @Column
    private Integer coordenadaY;


}