package model.dao;


public class DaoFactory {
    public static ContatoDaoJpa novoContatoDaoJpa() throws Exception{
        return new ContatoDaoJpa();
    }
}
