package net.engineeringdigest.journalApp.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "journal_entry", schema = "public")
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull(message = "Name must not be empty")
    private String title;

    @NotNull(message = "Name must not be empty")
    private String content;

    private LocalDateTime date;
}
