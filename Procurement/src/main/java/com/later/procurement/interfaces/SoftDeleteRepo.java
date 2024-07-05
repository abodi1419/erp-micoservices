package com.later.procurement.interfaces;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteRepo<T, ID> extends JpaRepository<T, ID> {

    @Query("select e from #{#entityName} e where e.deleted = false")
    @Override
    List<T> findAll();

    @Query("select e from #{#entityName} e where e.id in (:ids) and e.deleted = false")
    @Override
    List<T> findAllById(Iterable<ID> ids);

    @Query("select e from #{#entityName} e where e.id=:id and e.deleted = false")
    @Override
    Optional<T> findById(ID id);

    @Query("select e from #{#entityName} e where e.deleted=true")
    <S extends T> List<S> findAllDeleted();

    @Query("select e from #{#entityName} e where e.id=:id and e.deleted = true")
    Optional<T> findDeletedById(ID id);

    // TRANSACTIONALs

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=true, e.deletedAt=now() where e = :entity")
    @Override
    void delete(T entity);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=true, e.deletedAt=now() where e in (:entities)")
    @Override
    void deleteAllInBatch(Iterable<T> entities);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=true, e.deletedAt=now() where e.id = :id")
    @Override
    void deleteById(ID id);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=true, e.deletedAt=now() where e.id in (:ids)")
    @Override
    void deleteAllById(Iterable<? extends ID> ids);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=false, e.deletedAt=null where e =:supplierCertificate")
    void retrieve(T entity);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=false, e.deletedAt=null where e in (:entities)")
    void retrieveAll(Iterable<T> entities);

    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.deleted=false, e.deletedAt=null where e.id in (:ids)")
    void retrieveAllById(Iterable<? extends ID> ids);
}
