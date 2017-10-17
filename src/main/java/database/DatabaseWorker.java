package database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.entity.*;
import utils.HibernateUtil;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DatabaseWorker {

    private static EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
    public static Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
    private static Transaction transaction = session.getTransaction();

    public static boolean deleteHonor(HonorEntity honorEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            if (!session.contains(honorEntity)) {
                honorEntity = session.get(HonorEntity.class, honorEntity.getUuid());
            }
            transaction.begin();
            session.delete(honorEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {

        }
    }

    public static boolean deleteRank(RankEntity rankEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            if (!session.contains(rankEntity)) {
                rankEntity = session.get(RankEntity.class, rankEntity.getUuid());
            }
            transaction.begin();
            session.delete(rankEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean deleteVeteran(VeteranEntity veteranEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            if (!session.contains(veteranEntity)) {
                veteranEntity = session.get(VeteranEntity.class, veteranEntity.getUuid());
            }
            transaction.begin();
            session.delete(veteranEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean deleteWoundDisability(WoundDisabilityEntity woundDisabilityEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            if (!session.contains(woundDisabilityEntity)) {
                woundDisabilityEntity = session.get(WoundDisabilityEntity.class, woundDisabilityEntity.getUuid());
            }
            transaction.begin();
            session.delete(woundDisabilityEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean deleteWoundType(WoundTypeEntity woundTypeEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            if (!session.contains(woundTypeEntity)) {
                woundTypeEntity = session.get(WoundTypeEntity.class, woundTypeEntity.getUuid());
            }
            transaction.begin();
            session.delete(woundTypeEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static List<CategoryEntity> executeSelectCategoriesQuery() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<CategoryEntity> categories = session.createNativeQuery("SELECT * FROM categories", CategoryEntity.class).getResultList();


            return categories;
        } catch (Exception e) {


            throw e;
        }
    }

    public static List<DistrictEntity> executeSelectDistrictsQuery() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<DistrictEntity> districts = session.createNativeQuery("SELECT * FROM districts", DistrictEntity.class).getResultList();


            return districts;
        } catch (Exception e) {


            throw e;
        }
    }

    public static List<HonorEntity> executeSelectHonorsQuery() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<HonorEntity> honors = session.createNativeQuery("SELECT * FROM honors", HonorEntity.class).getResultList();


            return honors;
        } catch (Exception e) {


            throw e;
        }
    }

    public static List<RankEntity> executeSelectRanksQuery() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<RankEntity> ranks = session.createNativeQuery("SELECT * FROM ranks", RankEntity.class).getResultList();


            return ranks;
        } catch (Exception e) {


            throw e;
        }
    }

    public static List<VeteranEntity> executeSelectVeteransQuery(String query) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<VeteranEntity> persons = session.createNativeQuery(query, VeteranEntity.class).getResultList();

            return persons;
        } catch (Exception e) {


            throw e;
        }
    }

    public static List<WoundDisabilityEntity> executeSelectWoundDisabilitiesQuery() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<WoundDisabilityEntity> disabilities = session.createNativeQuery("SELECT * FROM wounddisabilities", WoundDisabilityEntity.class).getResultList();


            return disabilities;
        } catch (Exception e) {


            throw e;
        }
    }

    public static List<WoundTypeEntity> executeSelectWoundTypesQuery() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<WoundTypeEntity> types = session.createNativeQuery("SELECT * FROM woundtypes", WoundTypeEntity.class).getResultList();


            return types;
        } catch (Exception e) {


            throw e;
        }
    }

    public static int getPagesCount() {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            int pagesCount = Integer.parseInt(session.createNativeQuery("SELECT count(*) FROM veterans").getSingleResult().toString()) / 50 + 1;


            return pagesCount;
        } catch (Exception e) {


            throw e;
        }
    }

    public static UserEntity getUser(String login) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
            }
            List<UserEntity> usersList = session.createNativeQuery("SELECT * FROM users WHERE login='" + login + "'", UserEntity.class).getResultList();
            UserEntity user = usersList.size() > 0 ? usersList.get(0) : null;


            return user;
        } catch (Exception e) {


            throw e;
        }
    }

    public static boolean saveNewHonor(HonorEntity honorEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            transaction.begin();
            session.saveOrUpdate(honorEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean saveNewRank(RankEntity rankEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            transaction.begin();
            session.saveOrUpdate(rankEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean saveWoundType(WoundTypeEntity woundTypeEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            transaction.begin();
            session.saveOrUpdate(woundTypeEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean saveWoundDisability(WoundDisabilityEntity woundDisabilityEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            transaction.begin();
            session.saveOrUpdate(woundDisabilityEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }

    public static boolean saveVeteran(VeteranEntity veteranEntity) {
        try {
            if (!session.isOpen()) {
                session = entityManagerFactory.createEntityManager().unwrap(Session.class);
                transaction = session.getTransaction();
            }
            transaction.begin();
            session.saveOrUpdate(veteranEntity);
            session.flush();
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {


        }
    }
}
