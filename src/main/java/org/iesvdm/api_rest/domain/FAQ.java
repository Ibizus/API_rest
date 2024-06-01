package org.iesvdm.api_rest.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "faq",
        indexes = @Index(name = "faq_title_index", columnList = "title"))
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(length = 150)
    private String title;
    @Column(length = 600)
    private String content;
    private Boolean visible;
}
