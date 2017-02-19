package uz.com.platform.app.core;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long>,
        JpaSpecificationExecutor<T> {
    @Query("select e from #{#entityName} as e where e.uuid = :uuid")
    T findOne(@Param("uuid") String uuid);
}
