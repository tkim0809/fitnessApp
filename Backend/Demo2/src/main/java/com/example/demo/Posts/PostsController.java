package com.example.demo.Posts;

import com.example.demo.Posts.Comments;
import com.example.demo.Posts.PostsRepository;
import com.example.demo.StatPage.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsController {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PostVoteRepository postVoteRepository;

    @Autowired
    CommentVoteRepository commentVoteRepository;

    private final Logger logger = LoggerFactory.getLogger(PostsController.class);

    @PostMapping("/Posts")
    public Posts createPost(@RequestBody Posts post) {
        logger.info("Entered into Controller Layer for creating a new Post");
        Posts createdPost = postsRepository.save(post);
        logger.info("New Post created with ID:" + createdPost.getId());
        return createdPost;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Post/new")
    public List<Posts> getAllposts() {
        logger.info("Entered into Controller Layer");
        List<Posts> results = postsRepository.findAll();
        logger.info("Number of Posts Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}")
    public Posts findPostById(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        Posts result = postsRepository.findById(id).get();
        logger.info("Post Fetched:" + result.getId());
        return result;
    }



    @RequestMapping(method = RequestMethod.DELETE, path = "/Post/{postId}")
    public void deletePostById(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        postsRepository.deleteById(id);
        logger.info("Post Deleted:" + id);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/Post/{postId}/comment")
    public Comments createComment(@PathVariable("postId") int id, @RequestBody Comments comment) {
        logger.info("Entered into Controller Layer for creating a new Comment");
        Posts post = postsRepository.findById(id).get();
        comment.setPost(post);
        Comments createdComment = commentsRepository.save(comment);
        logger.info("New Comment created with ID:" + createdComment.getId());
        return createdComment;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}/comment")
    public List<Comments> getAllComments(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        logger.info("Number of Comments Fetched:" + results.size());
        return results;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}/comment/{commentId}")
    public Comments findCommentById(@PathVariable("postId") int id, @PathVariable("commentId") int commentId) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        Comments result = null;
        for (Comments c : results) {
            if (c.getId() == commentId) {
                result = c;
            }
        }
        logger.info("Comment Fetched:" + result.getId());
        return result;
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/Post/{postId}/comment/{commentId}")
    public void deleteCommentById(@PathVariable("postId") int id, @PathVariable("commentId") int commentId) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        Comments result = null;
        for (Comments c : results) {
            if (c.getId() == commentId) {
                result = c;
            }
        }
        commentsRepository.deleteById(result.getId());
        logger.info("Comment Deleted:" + result.getId());
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/upvote")
    public PostVote upvotePostById(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        PostVote result = postVoteRepository.findById(id).get();
        result.upvote();
        postVoteRepository.save(result);
        logger.info("Post Upvoted:" + result.getId());
        return result;
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/downvote")
    public PostVote downvotePostById(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        PostVote result = postVoteRepository.findById(id).get();
        result.downvote();
        postVoteRepository.save(result);
        logger.info("Post Downvoted:" + result.getId());
        return result;
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/withdrawvote")
    public PostVote withdrawvotePostById(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        PostVote result = postVoteRepository.findById(id).get();
        result.removeVote();
        postVoteRepository.save(result);
        logger.info("Post Downvoted:" + result.getId());
        return result;
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/comment/{commentId}/upvote")
    public CommentVotes upvoteCommentById(@PathVariable("postId") int id, @PathVariable("commentId") int commentId) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        Comments result = null;
        for (Comments c : results) {
            if (c.getId() == commentId) {
                result = c;
            }
        }
        CommentVotes commentVotes = new CommentVotes();
        commentVotes.setComment(result);
        commentVotes.upvote();
        commentVoteRepository.save(commentVotes);
        logger.info("Comment Upvoted:" + result.getId());
        return commentVotes;
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/comment/{commentId}/downvote")
    public CommentVotes downvoteCommentById(@PathVariable("postId") int id, @PathVariable("commentId") int commentId) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        Comments result = null;
        for (Comments c : results) {
            if (c.getId() == commentId) {
                result = c;
            }
        }
        CommentVotes commentVotes = new CommentVotes();
        commentVotes.setComment(result);
        commentVotes.downvote();
        commentVoteRepository.save(commentVotes);
        logger.info("Comment Upvoted:" + result.getId());
        return commentVotes;
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/comment/{commentId}/withdrawvote")
    public CommentVotes withdrawvoteCommentById(@PathVariable("postId") int id, @PathVariable("commentId") int commentId) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        Comments result = null;
        for (Comments c : results) {
            if (c.getId() == commentId) {
                result = c;
            }
        }
        CommentVotes commentVotes = new CommentVotes();
        commentVotes.setComment(result);
        commentVotes.removeVote();
        commentVoteRepository.save(commentVotes);
        logger.info("Comment Upvoted:" + result.getId());
        return commentVotes;
    }


//    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}/votes")
//    public int getVotesPosts(@PathVariable("postId") int id) {
//        logger.info("Entered into Controller Layer");
//        Posts result = postsRepository.findById(id).get();
//        logger.info("Votes Fetched:" + result.getVotes());
//        return result.getVotes();
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}/votes")
    public int getVotesPosts(@PathVariable("postId") int id) {
        logger.info("Entered into Controller Layer");
        PostVote result = postVoteRepository.findById(id).get();
        logger.info("Votes Fetched:" + result.getVote());
        return result.getVote();
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}/comment/{commentId}/votes")
    public int getVotesComments(@PathVariable("postId") int id, @PathVariable("commentId") int commentId) {
        logger.info("Entered into Controller Layer");
        Posts post = postsRepository.findById(id).get();
        List<Comments> results = commentsRepository.findByPost(post);
        Comments result = null;
        for (Comments c : results) {
            if (c.getId() == commentId) {
                result = c;
            }
        }
        CommentVotes commentVotes = commentVoteRepository.findByComment(result);

        logger.info("Votes Fetched:" + commentVotes.getVotes());
        return commentVotes.getVotes();
    }




//    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/upvote")
//    public Posts upvotePostById(@PathVariable("postId") int id) {
//        logger.info("Entered into Controller Layer");
//        Posts result = postsRepository.findById(id).get();
//        result.setVotes(result.getVotes() + 1);
//        postsRepository.save(result);
//        logger.info("Post Upvoted:" + result.getId());
//        return result;
//    }
//
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/downvote")
//    public Posts downvotePostById(@PathVariable("postId") int id) {
//        logger.info("Entered into Controller Layer");
//        Posts result = postsRepository.findById(id).get();
//        result.setVotes(result.getVotes() - 1);
//        postsRepository.save(result);
//        logger.info("Post Downvoted:" + result.getId());
//        return result;
//    }
//
//
//    @RequestMapping(method = RequestMethod.GET, path = "/Post/{postId}/votes")
//    public int getUpvotes(@PathVariable("postId") int id) {
//        logger.info("Entered into Controller Layer");
//        Posts result = postsRepository.findById(id).get();
//        logger.info("Number of votes:" + result.getVotes());
//        return result.getVotes();
//    }
//
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/removeupvote")
//    public Posts removeUpvote(@PathVariable("postId") int id) {
//        logger.info("Entered into Controller Layer");
//        Posts result = postsRepository.findById(id).get();
//        result.setVotes(result.getVotes() - 1);
//        postsRepository.save(result);
//        logger.info("Upvote Removed:" + result.getId());
//        return result;
//    }
//
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/Post/{postId}/removedownvote")
//    public Posts removeDownvote(@PathVariable("postId") int id) {
//        logger.info("Entered into Controller Layer");
//        Posts result = postsRepository.findById(id).get();
//        result.setVotes(result.getVotes() + 1);
//        postsRepository.save(result);
//        logger.info("Downvote Removed:" + result.getId());
//        return result;
//    }



}
