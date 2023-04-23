/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.porograma_06;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emili
 */
public class Daoventa implements IDAOGeneral<Venta,  Long>{

    @Override
    public Venta create(Venta p) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();

        session.save(p);

        for (Iterator<DetalleVenta> iterator
                = p.getDetalleventa().iterator(); iterator.hasNext();) {
            session.save(iterator.next());
        }

        t.commit();
        session.close();
        return p;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        //Venta v=
        boolean res = false;
        Venta venta = findById(id);
        if (venta == null) {
            res = false;
        } else {
            Query q = session.createQuery("delete DetalleVenta where Clave =" + id);
            q.executeUpdate();
            session.delete(venta);
            res = true;
        }
        t.commit();
        session.close();
        return res;

    }

    @Override
    public Venta update(Venta p, Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Venta vent = session.get(Venta.class, id);
        vent.setFecha(p.getFecha());
        vent.setMonto(p.getMonto());
        vent.setDetalleventa(p.getDetalleventa());
        session.update(vent);

        if (vent != null && p.getDetalleventa() != null) {;
            Query q = session.createQuery("update DetalleVenta where Clave =" + id);
            q.executeUpdate();
            for (DetalleVenta det : vent.getDetalleventa()) {
                det.setVenta(vent);
                session.save(det);
            }

        }
        t.commit();
        session.close();
        return vent;

    }

    @Override
    public List<Venta> findAll() {
        List<Venta> ventas = null;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        ventas = session.createQuery("from Venta").list();
        for (Venta venta : ventas) {
            List<DetalleVenta> detalle = null;
            detalle = session.createQuery("from DetalleVenta where Clave=" + venta.getClave()).list();
            for (DetalleVenta detalleVenta : detalle) {
                venta.getDetalleventa().add(detalleVenta);
            }
        }
        t.commit();
        return ventas;
    }

    @Override
    public Venta findById(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        List<DetalleVenta> detalle = null;
        detalle = session.createQuery("from DetalleVenta").list();
        Venta vent = session.get(Venta.class, id);

        if (vent != null) {
            for (DetalleVenta detalleVenta : detalle) {
                vent.getDetalleventa().add(detalleVenta);
            }
            t.commit();
            session.close();
            return vent;
        } else {
            return null;
        }
    }

}
    

