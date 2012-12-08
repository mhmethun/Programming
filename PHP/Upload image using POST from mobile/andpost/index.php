<?php

// Array holds all types of array that can occur here 
$upload_errors = array( 
    0    => "No errors.", 
    1    => "Larger than upload_max_filesize.", 
    2    => "Larger than form MAX_FILE_SIZE.", 
    3    => "Partial upload.", 
    4    => "No file.", 
    5    => "No temporary directory.", 
    6    => "Can't write to disk.", 
    7    => "File upload stopped by extension.", 
    8    => "File is empty." // add this to avoid an offset 
  ); 
  
// Upload directory
$target_path = "uploads/";

// Upload file path
// Here $_FILES['uploadedfile'] represend the file object that has send as POST to that page
// 'uploadedfile' represent the form name
// $_FILES['uploadedfile']['name'] represents the file name
// basename() method return the file name from a path, reference is below
// http://www.w3schools.com/php/func_filesystem_basename.asp
$target_path = $target_path . basename( $_FILES['uploadedfile']['name']);

// The move_uploaded_file() function moves an uploaded file to a new location.
// This function returns TRUE on success, or FALSE on failure.
// $_FILES['uploadedfile']['tmp_name'] represents the file content
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
echo "The file ". basename( $_FILES['uploadedfile']['name'])." has been uploaded";
} else{
echo "There was an error uploading the file, please try again!Error: ".$upload_errors[$_FILES['uploadedfile']['error']];
}

?>
