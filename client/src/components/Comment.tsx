



export  function Comment({comment, user}:CommentInterface) {
  return (
    <div className="comments">
      <h3>{user.username}</h3>
        <p>{comment}</p>
    </div>
  );
}
