function page(n, s) {
    if (n !== undefined)
        $("#pageNo").val(n);
    if (s !== undefined)
        $("#pageSize").val(s);
    //$("#searchForm").attr("action", "${ctx}/sys/org/list");
    $("#searchForm").submit();
    return false;
}

