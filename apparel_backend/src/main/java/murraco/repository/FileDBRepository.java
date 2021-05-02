package murraco.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import murraco.model.FileDB;
 
public interface FileDBRepository extends JpaRepository<FileDB, String> {
 
}
 