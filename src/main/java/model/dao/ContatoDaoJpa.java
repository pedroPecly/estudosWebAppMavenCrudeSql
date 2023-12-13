package model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import model.Contato;

public class ContatoDaoJpa implements InterfaceDao<Contato> {

    @Override
    public void incluir(Contato entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void editar(Contato entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Contato entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Contato.class, entidade.getId()));
            em.getTransaction();
        } finally {
            em.close();
        }

    }

    @Override
    public Contato pesquisarPorId(int id) throws Exception {
        Contato c = null;
        EntityManager em = ConnFactory.getEntityManager();
        try{
            em.getTransaction().begin();
            c = em.find(Contato.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return c;
    }

    @Override
    public List<Contato> listar() throws Exception {
        List<Contato> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Contato c").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return lista;
    }

}
