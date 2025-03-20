import { UserTitle } from "./UserTitle"
import '../styles/comment.scss';
import { stringToDate } from "../utils/stringToDate";

export  function Comment({comment, user, createAt}:CommentInterface) {
  return (
    <div className="comments">
        <UserTitle {...user}/>
        <span className="date">{stringToDate(createAt)}</span>
        <p>{comment}</p>
    </div>
  );
}
