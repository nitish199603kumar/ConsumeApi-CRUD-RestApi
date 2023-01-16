package com.nitish.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.nitish.model.CommentModel;
import com.nitish.model.PostModel;

public interface PostService {
	
	public PostModel[] getAllPosts();
	public CommentModel[] getAllCommentsForApost(Long postId);
	public PostModel createPost(PostModel postModel);
	public PostModel updatePost(PostModel postModel,Long id);
	public void deletePost(Long postId);
	

}
