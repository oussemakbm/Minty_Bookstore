<?php

// ini_set('post_max_size', '20M');
// ini_set('upload_max_filesize', '20M');

// if (isset($_FILES['image'])) {
//     $errors     = array();
//     $file_name  = $_FILES['image']['name'];
//     $file_size  = $_FILES['image']['size'];
//     $file_tmp   = $_FILES['image']['tmp_name'];
//     $file_type  = $_FILES['image']['type'];
//     $file_ext   = strtolower(end(explode('.', $_FILES['image']['name'])));

//     $extensions = array("jpeg", "jpg", "png");

//     if (in_array($file_ext, $extensions) === false) {
//         $errors[] = "extension not allowed, please choose a JPEG or PNG file.";
//     }

//     if (empty($errors) == true) {
//         move_uploaded_file($file_tmp, "profileImages/" . $file_name);
//         echo "Success";
//     } else {
//         print_r($errors);
//     }
// }

 
   $uploaddir = 'profileImages/';
   $uploadfile = $uploaddir . basename($_FILES['userfile']['name']);
 
   if (move_uploaded_file($_FILES['userfile']['tmp_name'], $uploadfile)) {
       echo "File is valid, and was successfully uploaded.\n";
       } else {
           echo "Possible file upload attack!\n";
       }
 
       echo 'Here is some more debugging info:';
       print_r($_FILES);



?>