import React from 'react';
import { useLocation } from 'react-router-dom';

export function useQuery() {
  // Used by product page to query products.
  const { search } = useLocation();
  return React.useMemo(() => new URLSearchParams(search), [search]);
}
