package br.lpm.business.KNearestNeighbors;

import br.lpm.business.datamodel.BaseDataPoint;

interface Knn<T> {
  T predict(BaseDataPoint attributes);
}
