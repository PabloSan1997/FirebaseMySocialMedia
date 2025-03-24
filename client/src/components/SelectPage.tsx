import { Link, useSearchParams } from "react-router-dom";
import '../styles/selectpage.scss';


export function SelectPage({path}:{path:string}) {
    const [search] = useSearchParams();
    const searchpage = Number(search.get('page'));
    const page = isNaN(searchpage)?0:searchpage;

  return (
    <div className="move_select">
        {page >= 1 && <Link to={`${path}page=${page - 1}`} id="nextpage">←</Link>}
        <span>{page}</span>
        <Link to={`${path}page=${page + 1}`} id="lastpage">→</Link>
    </div>
  );
}
