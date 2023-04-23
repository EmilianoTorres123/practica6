/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.uv.porograma_06;

import java.util.List;

/**
 *
 * @author emili
 */
public interface IDAOGeneral <T,ID>{
    public T create(T p);//T->POJO
    //El create necesita recibir el POJO
    public boolean delete(ID id);
    public T update(T p, ID id);
    
    public List<T> findAll();
    public T findById(ID id);//Regresa elemento de tipo T con el ID.
}
