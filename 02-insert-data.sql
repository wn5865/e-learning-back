# Insert data
USE udemy;

INSERT INTO `level`(`level_name`)
VALUES ('전체'),
       ('전문가'),
       ('중급자'),
       ('초보자');

INSERT INTO `course_category`(`name`)
VALUES ('IT 및 소프트웨어'),
       ('드로잉'),
       ('디자인'),
       ('재무 및 회계'),
       ('사진'),
       ('경영');

INSERT INTO `user`(`name`, `email`)
VALUES ('Jiwon Yoon', 'foo@gmail.com');

INSERT INTO `image`(`name`, `type`, `url`)
VALUES ('1.jpeg', 'image/jpeg', 'assets/images/courses/1.jpeg'),
       ('2.jpeg', 'image/jpeg', 'assets/images/courses/2.jpeg'),
       ('3.jpeg', 'image/jpeg', 'assets/images/courses/3.jpeg'),
       ('4.jpeg', 'image/jpeg', 'assets/images/courses/4.jpeg');

# course
INSERT INTO `course`(`instructor_id`, `category_id`, `level_id`,
                     `price`, `num_students`, `num_ratings`, `total_rating`,
                     `title`, `subtitle`, `description`)
VALUES (1, 1, 1, 15000, 30, 30, 120,
        'Build Responsive Real-World Websites with HTML and CSS',
        'Learn modern HTML5, CSS3 and web design by building a stunning website for your portfolio! Includes flexbox and CSS Grid',
        '*** The #1 bestselling HTML and CSS course on Udemy! ***
*** Completely re-built from scratch in July 2021 (35+ hours video) ***
"Having gone through other related courses on other platforms, I can say this course is the most practical and readily applicable course on web design and development I have taken." — Bernie Pacis
Open a new browser tab, type in www.omnifood.dev, and take a look around. I will wait here...
Amazing, right? What if you knew exactly how to design and build a website like that, completely from scratch? How amazing would that be?
Well, I''m here to teach you HTML, CSS, and web design, all by building the stunning website that you just saw, step-by-step.
So, after finishing this course, you will know exactly how to build a beautiful, professional, and ready-to-launch website just like Omnifood, by following a 7-step process. And it will even look great on any computer, tablet, and smartphone.
But what if you want to build a completely different website? Well, no problem! I designed the course curriculum with exactly this goal: to enable you to design and build any website that you can think of, not just copy the course project.
[01] Why should you learn HTML and CSS in the first place?'),
       (1, 1, 3, 50000, 1000, 100, 450,
        'The Complete JavaScript Course 2022: From Zero to Expert!',
        'The modern JavaScript course for everyone! Master JavaScript with projects, challenges and theory. Many courses in one!',
        'Description
*** The #1 bestselling HTML and CSS course on Udemy! ***
*** Completely re-built from scratch in July 2021 (35+ hours video) ***
"Having gone through other related courses on other platforms, I can say this course is the most practical and readily applicable course on web design and development I have taken." — Bernie Pacis
Open a new browser tab, type in www.omnifood.dev, and take a look around. I will wait here...
Amazing, right? What if you knew exactly how to design and build a website like that, completely from scratch? How amazing would that be?
Well, I''m here to teach you HTML, CSS, and web design, all by building the stunning website that you just saw, step-by-step.
So, after finishing this course, you will know exactly how to build a beautiful, professional, and ready-to-launch website just like Omnifood, by following a 7-step process. And it will even look great on any computer, tablet, and smartphone.
But what if you want to build a completely different website? Well, no problem! I designed the course curriculum with exactly this goal: to enable you to design and build any website that you can think of, not just copy the course project.
How to design beautiful websites, by learning a web design framework I created just for this course. It consists of easy-to-use guidelines for design aspects like typography, colors, images, spacing, and more (this is like a small standalone course!).
How to use well-established website components and layout patterns in order to come up with professional-looking designs
How to make any website work on any mobile device, no matter the design and layout (responsive design)
How to use the 7 steps of building a professional website in practice: planning, sketching, designing, building, testing, optimizing, and launching'),
       (1, 2, 2, 30000, 50000, 1010, 4100,
        'The Ultimate Drawing Course - Beginner to Advanced',
        'Learn the #1 most important building block of all art, Drawing. This course will teach you how to draw like a pro!',
        'Join over 450,000 learning student and start gaining the drawing skills you''ve always wanted.
Description
*** The #1 bestselling HTML and CSS course on Udemy! ***
*** Completely re-built from scratch in July 2021 (35+ hours video) ***
"Having gone through other related courses on other platforms, I can say this course is the most practical and readily applicable course on web design and development I have taken." — Bernie Pacis
Open a new browser tab, type in www.omnifood.dev, and take a look around. I will wait here...
Amazing, right? What if you knew exactly how to design and build a website like that, completely from scratch? How amazing would that be?
Well, I''m here to teach you HTML, CSS, and web design, all by building the stunning website that you just saw, step-by-step.
So, after finishing this course, you will know exactly how to build a beautiful, professional, and ready-to-launch website just like Omnifood, by following a 7-step process. And it will even look great on any computer, tablet, and smartphone.
But what if you want to build a completely different website? Well, no problem! I designed the course curriculum with exactly this goal: to enable you to design and build any website that you can think of, not just copy the course project.
So, in order to become a confident and independent developer, capable of building your own websites in the future, you will learn:
The fundamentals of modern and semantic HTML, CSS, and building layouts in a small separate project, which will prepare you for the main course project (www.omnifood.dev). This includes modern flexbox and CSS Grid!
How to design beautiful websites, by learning a web design framework I created just for this course. It consists of easy-to-use guidelines for design aspects like typography, colors, images, spacing, and more (this is like a small standalone course!).
How to use well-established website components and layout patterns in order to come up with professional-looking designs
How to make any website work on any mobile device, no matter the design and layout (responsive design)
How to use the 7 steps of building a professional website in practice: planning, sketching, designing, building, testing, optimizing, and launching'),
       (1, 2, 1, 30000, 1000, 500, 2000,
        'Character Art School: Complete Character Drawing Course',
        'Learn How to Draw People and Character Drawings Professionally, Drawing for Animation, Comics, Cartoons, Games and More!',
'Description
*** The #1 bestselling HTML and CSS course on Udemy! ***
*** Completely re-built from scratch in July 2021 (35+ hours video) ***
"Having gone through other related courses on other platforms, I can say this course is the most practical and readily applicable course on web design and development I have taken." — Bernie Pacis
Open a new browser tab, type in www.omnifood.dev, and take a look around. I will wait here...
Amazing, right? What if you knew exactly how to design and build a website like that, completely from scratch? How amazing would that be?
Well, I''m here to teach you HTML, CSS, and web design, all by building the stunning website that you just saw, step-by-step.
So, after finishing this course, you will know exactly how to build a beautiful, professional, and ready-to-launch website just like Omnifood, by following a 7-step process. And it will even look great on any computer, tablet, and smartphone.
But what if you want to build a completely different website? Well, no problem! I designed the course curriculum with exactly this goal: to enable you to design and build any website that you can think of, not just copy the course project.
So, in order to become a confident and independent developer, capable of building your own websites in the future, you will learn:
The fundamentals of modern and semantic HTML, CSS, and building layouts in a small separate project, which will prepare you for the main course project (www.omnifood.dev). This includes modern flexbox and CSS Grid!
How to design beautiful websites, by learning a web design framework I created just for this course. It consists of easy-to-use guidelines for design aspects like typography, colors, images, spacing, and more (this is like a small standalone course!).
How to use well-established website components and layout patterns in order to come up with professional-looking designs');

# chapers
INSERT INTO `chapter`(`course_id`, `sort_order`, `title`)
VALUES (1, 1, 'HTML Fundamentals'),
       (1, 2, 'CSS Fundamentals'),
       (2, 1, 'JavaScript Fundamentals - Part 1'),
       (2, 2, 'JavaScript Fundamentals - Part 2'),
       (3, 1, 'Getting started with the course'),
       (3, 2, 'Learn how to draw a realistic eye'),
       (4, 1, 'Getting started with the course'),
       (4, 2, 'Learn how to draw a realistic eye');

# lectures
INSERT INTO `lecture`(`chapter_id`, `sort_order`, `title`)
VALUES (1, 1, 'Introduction to HTML'),
       (1, 2, 'HTML Document Structure'),
       (2, 1, 'Introduction to CSS'),
       (2, 2, 'Inline, Internal and External CSS'),
       (3, 1, 'Introduction'),
       (3, 2, 'Hello World'),
       (4, 1, 'Introduction'),
       (4, 2, 'Activating Strict Mode'),
       (5, 1, 'Introduction'),
       (5, 2, 'Learn how to get a guaranteed win out of this course'),
       (6, 1, 'Drawing an eye step by step'),
       (6, 2, 'Adding the detail to your eye drawing'),
       (7, 1, 'The Basic Structure of Art and Drawing'),
       (7, 2, 'Breaking Down the Structure'),
       (8, 1, 'Drawing Warmup and Penmanship'),
       (8, 2, 'Drawing in 3D');

# videos
INSERT INTO `video`(`lecture_id`, `name`, `type`, `url`)
VALUES (1, '1.mp4', 'video/mp4', '/assets/videos/1.mp4'),
       (2, '2.mp4', 'video/mp4', '/assets/videos/2.mp4'),
       (3, '3.mp4', 'video/mp4', '/assets/videos/3.mp4'),
       (4, '4.mp4', 'video/mp4', '/assets/videos/4.mp4'),
       (5, '1.mp4', 'video/mp4', '/assets/videos/1.mp4'),
       (6, '2.mp4', 'video/mp4', '/assets/videos/2.mp4'),
       (7, '3.mp4', 'video/mp4', '/assets/videos/3.mp4'),
       (8, '4.mp4', 'video/mp4', '/assets/videos/4.mp4'),
       (9, '1.mp4', 'video/mp4', '/assets/videos/1.mp4'),
       (10, '2.mp4', 'video/mp4', '/assets/videos/2.mp4'),
       (11, '3.mp4', 'video/mp4', '/assets/videos/3.mp4'),
       (12, '4.mp4', 'video/mp4', '/assets/videos/4.mp4'),
       (13, '1.mp4', 'video/mp4', '/assets/videos/1.mp4'),
       (14, '2.mp4', 'video/mp4', '/assets/videos/2.mp4'),
       (15, '3.mp4', 'video/mp4', '/assets/videos/3.mp4'),
       (16, '4.mp4', 'video/mp4', '/assets/videos/4.mp4');

# reviews
INSERT INTO `review`(`user_id`, `course_id`, `content`, `rating`)
VALUES (1, 1, '감사합니다 최고의 강의입니다!', 5),
       (1, 1, '감사합니다 많은 도움이 되었습니다', 4),
       (1, 1, '정말 잘 이해되게 설명하세요', 5),
       (1, 1, '감사합니다 최고의 강의입니다!', 3),
       (1, 1, '감사합니다 최고의 강의입니다!', 2),
       (1, 1, '감사합니다 최고의 강의입니다!', 3),
       (1, 1, '감사합니다 최고의 강의입니다!', 5);

update course cross join image
set course.image_id = image.id
where substring(image.name, 1, 1) = course.id;