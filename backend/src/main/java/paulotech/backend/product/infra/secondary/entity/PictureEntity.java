package paulotech.backend.product.infra.secondary.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import paulotech.backend.product.domain.aggregates.Picture;

import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "product_picture")
@Setter
@Getter
@Builder(toBuilder = true)
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pictureSequence")
    @SequenceGenerator(name = "pictureSequence", sequenceName = "product_picture_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type", nullable = false)
    private String mimeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_fk", nullable = false)
    private ProductEntity product;

    public PictureEntity() {
    }

    public PictureEntity(Long id, byte[] file, String mimeType, ProductEntity product) {
        this.id = id;
        this.file = file;
        this.mimeType = mimeType;
        this.product = product;
    }

    public static PictureEntity fromSingle(Picture picture) {
        if (picture == null) {
            return null;
        }
        return PictureEntity.builder()
                .file(picture.file())
                .mimeType(picture.mimeType())
                .build();
    }

    public static Picture to(PictureEntity pictureEntity) {
        if (pictureEntity == null) {
            return null;
        }
        return Picture.builder()
                .file(pictureEntity.getFile())
                .mimeType(pictureEntity.getMimeType())
                .build();
    }

    public static Set<PictureEntity> from(List<Picture> pictures) {
        if (pictures == null) {
            return new HashSet<>();
        }
        return pictures.stream()
                .filter(Objects::nonNull)
                .map(PictureEntity::fromSingle)
                .collect(Collectors.toSet());
    }

    public static List<Picture> to(Set<PictureEntity> pictureEntities) {
        if (pictureEntities == null) {
            return new ArrayList<>();
        }
        return pictureEntities.stream()
                .filter(Objects::nonNull)
                .map(PictureEntity::to)
                .collect(Collectors.toList());
    }

//    public static PictureEntity fromSingle(Picture picture) {
//        return PictureEntity.builder()
//                .file(picture.file())
//                .mimeType(picture.mimeType())
//                .build();
//    }
//
//    public static Picture to(PictureEntity pictureEntity) {
//        return Picture.builder()
//                .file(pictureEntity.getFile())
//                .mimeType(pictureEntity.getMimeType())
//                .build();
//    }
//
//    public static Set<PictureEntity> from(List<Picture> pictures) {
//        return pictures.stream().map(PictureEntity::fromSingle).collect(Collectors.toSet());
//    }
//
//    public static List<Picture> to(Set<PictureEntity> pictureEntities) {
//        return pictureEntities.stream().map(PictureEntity::to).collect(Collectors.toList());
//    }


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
