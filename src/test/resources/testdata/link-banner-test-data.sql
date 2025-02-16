
-- Insert test data into banner and link_banner
INSERT INTO public.link_banner (text, url,  created_on, last_updated)
VALUES ('Test Banner', 'http://example.com', now(), now());

-- Insert data for deletion purpose
INSERT INTO public.link_banner (text, url,  created_on, last_updated)
VALUES ('Delete Banner', 'http://delete.com', now(), now());