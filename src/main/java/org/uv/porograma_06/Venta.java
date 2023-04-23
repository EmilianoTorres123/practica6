/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.porograma_06;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author emili
 */
@Entity
@Table(name="Venta")
public class Venta {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clave")
    private long clave;
    
    @Column(name="monto")
    private double monto;
    
    @Column(name="fecha")
    private Date fecha;
    
    @OneToMany(mappedBy="venta")
    private Set<DetalleVenta>detalleventa;
    
    public Venta(){
        detalleventa=new HashSet<>();
    }

    public Set<DetalleVenta> getDetalleventa() {
        return detalleventa;
    }

    public void setDetalleventa(Set<DetalleVenta> detalleventa) {
        this.detalleventa = detalleventa;
    }

    public long getClave() {
        return clave;
    }

    public void setClave(long clave) {
        this.clave = clave;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
