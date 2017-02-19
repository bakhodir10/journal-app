package uz.com.journal.core;

import org.springframework.data.repository.NoRepositoryBean;
import uz.com.platform.app.core.BaseRepository;

@NoRepositoryBean
public interface NonDeletableRepository<T extends NonDeletable> extends BaseRepository<T> {
}
