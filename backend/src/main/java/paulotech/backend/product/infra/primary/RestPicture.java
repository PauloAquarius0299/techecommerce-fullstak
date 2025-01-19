package paulotech.backend.product.infra.primary;

import lombok.Builder;
import paulotech.backend.product.domain.aggregates.Picture;
import paulotech.backend.shared.error.domain.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record RestPicture(byte[] file,
                          String mimeType) {

    public RestPicture {
        Assert.notNull("file", file);
        Assert.notNull("mimeType", mimeType);
    }

    public static Picture toDomain(RestPicture restPicture){
        return Picture.builder()
                .file(restPicture.file())
                .mimeType(restPicture.mimeType())
                .build();
    }
    public static RestPicture fromDomain(Picture picture){
        return RestPicture.builder()
                .file(picture.file())
                .mimeType(picture.mimeType())
                .build();
    }

    public static List<Picture> toDomain(List<RestPicture> restPictures){
        return restPictures.stream().map(RestPicture::toDomain).collect(Collectors.toList());
    }

    public static List<RestPicture> fromDomain(List<Picture> pictures){
        return pictures.stream().map(RestPicture::fromDomain).collect(Collectors.toList());
    }
}
