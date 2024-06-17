package net.engineeringdigest.journalApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.engineeringdigest.journalApp.Entity.JournalEntry;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

}
