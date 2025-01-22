export interface BaseUrl {
  firstName?: string;
  lastName?: string;
  email?: string;
  imageUrl?: string;
  publicId?: string;
}

export interface ConnectedUser extends BaseUrl {
  authorities?: string[];
}