package minjae5024.marketPrice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductCode is a Querydsl query type for ProductCode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductCode extends EntityPathBase<ProductCode> {

    private static final long serialVersionUID = 270288103L;

    public static final QProductCode productCode = new QProductCode("productCode");

    public final StringPath prdlstCd = createString("prdlstCd");

    public final StringPath prdlstNm = createString("prdlstNm");

    public QProductCode(String variable) {
        super(ProductCode.class, forVariable(variable));
    }

    public QProductCode(Path<? extends ProductCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductCode(PathMetadata metadata) {
        super(ProductCode.class, metadata);
    }

}

