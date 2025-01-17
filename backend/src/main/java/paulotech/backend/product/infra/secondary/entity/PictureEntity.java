package paulotech.backend.product.infra.secondary.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import paulotech.backend.product.domain.aggregates.Picture;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "product_picture")
@Setter
@Getter
@Builder
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pictureSequence")
    @SequenceGenerator(name = "pictureSequence", sequenceName = "picture_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type", nullable = false)
    private String mimeType;

    @ManyToOne
    @JoinColumn(name = "product_fk", referencedColumnName = "id")
    private ProductEntity product;

    public PictureEntity(Long id, byte[] file, String mimeType, ProductEntity product) {
        this.id = id;
        this.file = file;
        this.mimeType = mimeType;
        this.product = product;
    }

    public static PictureEntity from(List<PictureEntity> picture){
        return PictureEntity.builder()
                .file(picture.file())
                .mimeType(picture.mimeType())
                .build();
    }

    public static Set<PictureEntity> from(List<Picture> pictures) {
        return pictures.stream().map(PictureEntity::from).collect(Collectors.toSet());
    }

    public Picture to() {
        return new Picture(this.file, this.mimeType);
    }

    public static List<Picture> to(Set<PictureEntity> pictureEntities){
        return pictureEntities.stream().map(PictureEntity::to).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PictureEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
