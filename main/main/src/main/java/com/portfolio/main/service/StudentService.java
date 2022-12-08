package com.portfolio.main.service;


import com.portfolio.main.model.Image;
import com.portfolio.main.model.Student;
import com.portfolio.main.model.User;
import com.portfolio.main.repositories.StudentRepository;
import com.portfolio.main.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public List<Student> listStudents(String FIO) {
        if(FIO != null) return studentRepository.findByFIO(FIO);
        return studentRepository.findAll();
    }
    public void saveStudent(Principal principal, Student student, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        student.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            student.addImageToStudent(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            student.addImageToStudent(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            student.addImageToStudent(image3);
        }
        log.info("Saving new student. FIO{}; Grade{}",student.getFIO(),student.getGrade(),student.getUser().getEmail());
        Student studentFromDb = studentRepository.save(student);
        studentFromDb.setPreviewImageId(studentFromDb.getImages().get(0).getId());
        studentRepository.save(student);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }


    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student getStudentById(Long id) {
    return  studentRepository.findById(id).orElse(null);
    }

}
