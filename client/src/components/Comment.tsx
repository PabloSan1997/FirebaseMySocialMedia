import { UserTitle } from "./UserTitle"




export  function Comment({comment, user}:CommentInterface) {
  return (
    <div className="comments">
        <UserTitle {...user}/>
        <p>{comment}</p>
    </div>
  );
}
