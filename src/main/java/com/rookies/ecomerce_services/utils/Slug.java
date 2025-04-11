package com.rookies.ecomerce_services.utils;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Component;

@Component
public class Slug {
    public String generateSlug(String name) {
        Slugify slugify = new Slugify();
        return slugify.slugify(name);
    }
}
