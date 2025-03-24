


export function stringToDate(timesta: string, onlydate = false): string {
    const date = new Date(timesta);

    if (!onlydate)
        return date.toLocaleString();
    return date.toLocaleDateString();
}

