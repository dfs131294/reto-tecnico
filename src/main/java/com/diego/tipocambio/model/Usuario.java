package com.diego.tipocambio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Usuario {

    @Id
    private Long id;

    private String usuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasenha;

}