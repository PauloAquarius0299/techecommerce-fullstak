import { ProductPictures } from "../admin/model/product.model";

export interface CartItemAdd {
  publicId: string;
  quantity: number;
}

export interface Cart {
  products: CartItem[];
}

export interface CartItem {
  name: string;
  price: number;
  brand: string;
  picture: ProductPictures;
  quantity: number;
  publicId: string;
}

export interface StripeSession {
  id: string;
}
