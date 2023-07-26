package com.example.demo.repository;

import com.example.demo.repository.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Integer> {
}
