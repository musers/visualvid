export interface CategoryNode {
  name: string;
  type?: string;
  id? : string;
  children?: CategoryNode[];
}

// TODO Need to merge with above
export interface Category {
  categoryId: String;
  categoryName: String;
  id?: string;
  nameFormatted?: string;
}
