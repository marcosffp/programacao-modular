package br.lpm.business.KNearestNeighbors;

import br.lpm.business.datamodel.DataPoint;

interface Knn<T> {
  T predict(DataPoint attributes);
}
