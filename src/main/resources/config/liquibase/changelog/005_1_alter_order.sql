ALTER TABLE public.cart_order ALTER COLUMN user_id TYPE uuid USING user_id::uuid;
