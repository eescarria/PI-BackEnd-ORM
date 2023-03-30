package com.example.clinicaOdontologica.domain;

import javax.persistence.*;

@Entity
@Table(name="domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String calle;
    @Column
    private String numero;
    @Column
    private String ciudad;
    @Column
    private String departamento;

    public Domicilio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
