package service;

import java.util.List;
import dao.PublicationDao;
import dao.mysql.PublicationDaoImpl;
import domain.Publication;
import exception.PersistentException;

public class PublicationServiceImpl extends ServiceImpl implements PublicationService {

    @Override
    public List<Publication> findAll() throws PersistentException {
        PublicationDao dao = new PublicationDaoImpl();
        return dao.read();
    }

    @Override
    public Publication findById(Integer id) throws PersistentException {
        PublicationDao dao = new PublicationDaoImpl();
        return dao.read(id);
    }

    @Override
    public Publication findByIssn(Integer issn) throws PersistentException {
        PublicationDao dao = new PublicationDaoImpl();
        return dao.readByIssn(issn);
    }

    @Override
    public List<Publication> findByTitleLike(String title) throws PersistentException {
        PublicationDao dao = new PublicationDaoImpl();
        return dao.readByTitleLike(title);
    }

    @Override
    public void save(Publication publication) throws PersistentException {
        PublicationDao dao = new PublicationDaoImpl();
        if (publication.getId() != null) {
            dao.update(publication);
        } else {
            publication.setId(dao.create(publication));
        }
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        PublicationDao dao = new PublicationDaoImpl();
        dao.delete(id);
    }
}
